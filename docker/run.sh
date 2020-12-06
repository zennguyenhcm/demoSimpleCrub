read -s -p "user: " -r USER
echo

read -s -p "password: " -r PASSWORD
echo

read -s -p "db: " -r DATABASE
echo


echo docker run --rm -e MYSQL_PASSWORD="$PASSWORD" -e MYSQL_USER="$USER" -e MYSQL_DATABASE="$DATABASE" --name="mysqldockercontainer" -d mysql/mysql-server:latest
docker run -d --rm -e MYSQL_PASSWORD="$PASSWORD" -e MYSQL_USER="$USER" -e MYSQL_DATABASE="$DATABASE" --name="mysqldockercontainer" mysql/mysql-server:latest

sleep 2
docker ps --all
sudo chmod -R 777 /var/lib/mysql/
docker exec -it "mysqldockercontainer" mysql -u "$USER" -p "$PASSWORD"
