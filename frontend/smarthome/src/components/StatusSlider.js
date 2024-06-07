import React, { useState } from 'react'; // Import React and useState hook from React library

import Slider from '@mui/material/Slider'; // Import Slider component from Material-UI library
import Button from '@mui/material/Button'; // Import Button component from Material-UI library

import '../css/StatusSlider.css'; // Import CSS file for styling

// Function to validate a number and provide a default value if it's not a valid number
const isValidNumber = (value) => {
    const number = parseInt(value, 10); // Convert the value to an integer with base 10
    return !isNaN(number) ? number : 100; // If the value is a valid number, return it; otherwise, return 100
};

// StatusSlider component: Renders a slider input to set a status and a button to update it
const StatusSlider = ({ initialStatus, onUpdate, actuatorId }) => {
    // Define state variables using the useState hook
    const [newStatus, setNewStatus] = useState(isValidNumber(initialStatus)); // State variable for the current status
    const [loading, setLoading] = useState(false); // State variable to track loading state
    const [feedback, setFeedback] = useState(null); // State variable for feedback message
    const [unsavedChanges, setUnsavedChanges] = useState(false); // State variable to track unsaved changes

    // Event handler for slider value change
    const handleSliderChange = (event, newValue) => {
        setNewStatus(newValue); // Update the newStatus state with the new value from the slider
        setUnsavedChanges(true); // Set unsaved changes flag when slider is moved
    };

    // Event handler for setting status
    const handleSetStatus = () => {
        setLoading(true); // Set loading state to true
        setFeedback(null); // Clear any existing feedback message
        setUnsavedChanges(false); // Reset unsaved changes flag when status is set

        // Log the request details
        console.log(`Sending request to http://localhost:8080/actuators/${actuatorId}/act with command: ${newStatus}`);

        // Make a POST request to update the status
        fetch(`http://localhost:8080/actuators/${actuatorId}/act?command=${newStatus}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // Specify content type as JSON
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok'); // Throw an error if response is not ok
                }
                return response.json(); // Parse response body as JSON
            })
            .then(data => {
                // Log the response details
                console.log('Received response:', data);

                // Parse the new status from API response
                const newStatusFromApi = parseInt(data.status, 10);
                if (!isNaN(newStatusFromApi)) { // If the new status is a valid number
                    setNewStatus(newStatusFromApi); // Update the newStatus state
                    onUpdate(newStatusFromApi); // Call the onUpdate function with the new status
                    setFeedback('New position set'); // Set feedback message
                    setTimeout(() => {
                        setFeedback(null); // Remove feedback after 3 seconds
                    }, 3000);
                } else {
                    console.error('Invalid status received from API:', data.status); // Log error for invalid status
                    setFeedback('Set failed'); // Set feedback message for failure
                }
            })
            .catch(error => {
                console.error('Error updating status:', error); // Log error for updating status
                setFeedback('Error updating status'); // Set feedback message for error
            })
            .finally(() => {
                setLoading(false); // Set loading state to false after completion
            });
    };

    // Render the StatusSlider component
    return (
        <div className="status-slider-container"> {/* Container for the slider and button */}
            {/* Slider component to set the status */}
            <Slider
                value={newStatus} // Current value of the slider
                onChange={handleSliderChange} // Event handler for value change
                aria-labelledby="discrete-slider" // ID of the element describing the slider
                valueLabelDisplay="auto" // Display value label automatically
                step={1} // Step size of the slider
                marks // Show marks at regular intervals
                min={0} // Minimum value of the slider
                max={100} // Maximum value of the slider
            />
            {/* Button to set the status */}
            <Button
                variant="contained" // Button variant (contained)
                onClick={handleSetStatus} // Event handler for button click
                disabled={loading} // Disable button when loading
                className={`status-slider-button ${unsavedChanges ? 'unsaved-changes' : 'grey'}`} /* Add or remove class based on unsaved changes */
            >
                {loading ? 'Setting...' : 'Set'} {/* Button text based on loading state */}
            </Button>
            {/* Display feedback message */}
            {feedback && (
                <p className={feedback === 'New position set' ? 'success-feedback' : 'error-feedback'}>
                    {feedback}
                </p>
            )}
        </div>
    );
};

export default StatusSlider; // Export the StatusSlider component
