FROM node:14 as build

WORKDIR /app

# Copiar apenas os arquivos necessários
COPY ./frontend/smarthome/package*.json ./

RUN npm install

# Copiar o restante dos arquivos do projeto React
COPY ./frontend/smarthome ./

RUN npm run build

FROM nginx:alpine

# Copiar os arquivos de build para o diretório correto do Nginx
COPY --from=build /app/build /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]