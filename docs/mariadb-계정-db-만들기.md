1. 모든 작업은 dba(root)로 한다.

```sh
# mysql -u root -p
```

2. 데이터베이스 생성
```sh
MariaDB [(none)]>  create database webdb;
MariaDB [(none)]> show databases;
```

3. 사용자 생성
```sh
MariaDB [(none)]>  create user 'webdb'@'localhost' identified by 'mysql123';
MariaDB [(none)]>  create user 'webdb'@'192.168.%' identified by 'mysql123';
MariaDB [(none)]> show databases;
```

4. 권한주기
MariaDB [(none)]> grant all  privileges on webdb.* to 'webdb'@'localhost';
MariaDB [(none)]> flush privileges;

5. 확인하기
```sh
# mysql -u webdb -D webdb -p
패스워드 > 
```
