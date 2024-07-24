-- init-db.sql

-- Cria o banco de dados
CREATE DATABASE IF NOT EXISTS SmartHome;

-- Cria o usuário e concede todos os privilégios
CREATE USER IF NOT EXISTS 'smarthome'@'%' IDENTIFIED BY 'smarthome';
GRANT ALL PRIVILEGES ON SmartHome.* TO 'smarthome'@'%';

-- Aplica as permissões
FLUSH PRIVILEGES;
