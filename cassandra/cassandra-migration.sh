echo "*** Starting Cassandra"
sed -i s/Xss180k/Xss256k/ /etc/cassandra/cassandra-env.sh
/usr/sbin/cassandra
sleep 10

echo "*** Importing Scheme"
wget https://raw2.github.com/twitter/zipkin/master/zipkin-cassandra/src/schema/cassandra-schema.txt
cassandra-cli -host localhost -port 9160 -f cassandra-schema.txt

echo "*** Stopping Cassandra"
kill -9 $(ps aux | grep -i cassandra | grep -v grep | grep -v cassandra-migration | awk '{print $2}')

mv /etc/cassandra/cassandra.yaml /etc/cassandra/cassandra.default.yaml

echo "*** Zipkin migration complete"
