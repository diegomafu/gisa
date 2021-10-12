#!/bin/sh

sqoop import --connect jdbc:mysql://mysqlsrv/saf --table Exemplo --username root --password mysql@123 -m 1

sqoop import --connect jdbc:mysql://mysqlsrv/saf --query 'SELECT id, nome, idade, data, valor FROM Exemplo WHERE $CONDITIONS AND idade < 100' --where "idade > 10" --username root --password mysql@123 --target-dir /target/ --split-by id