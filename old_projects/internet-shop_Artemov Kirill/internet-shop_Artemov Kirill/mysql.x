mysql --default-character-set=utf8 -uroot < eshop.sql
mysqldump -u root -p -f mydatabase > /home/myname/mydatabasedump.sql
mysql -u root -p -f mydatabase < /home/myname/mydatabasedump.sql