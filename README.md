# PROJETO GISA

# Comando para executar:
docker-compose up

# Comando para listar processos docker:
docker ps -a

# Comando para entrar no container do sqoop:
docker exec -it <PID> /bin/bash

# Comando para executar o sqoop import(Dentro do container de sqoop)
sqoop import --connect jdbc:mysql://mysqlsrv/saf --table Exemplo --username root --password mysql@123 -m 1

# -----------------------------------------------------
# Comandos auxiliares - (Dentro do container kafka)
# -----------------------------------------------------
Comando para ver mensagens tópico Kafka:
kafka-console-consumer --bootstrap-server localhost:9092 --topic gisa-topic --from-beginning --max-messages 10

Comando para ver tópico:
kafka-topics --describe --topic gisa-topic --zookeeper zookeeper:2181

Comando para criar tópico:
kafka-topics --create --topic gisa-topic --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181