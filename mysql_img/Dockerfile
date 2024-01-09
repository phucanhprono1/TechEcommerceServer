ARG image_name=techecommerce_mysql

FROM mysql

# Set the root password for the MySQL server
ENV MYSQL_ROOT_PASSWORD=root

COPY ./init.sql /docker-entrypoint-initdb.d/
VOLUME /var/lib/rest_mysql

# Expose the MySQL port
EXPOSE 3306

label image.name=$image_name