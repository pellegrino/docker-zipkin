echo "*** Adding Cassandra deb source"
cat << EOF >> /etc/apt/sources.list
deb http://www.apache.org/dist/cassandra/debian 12x main
deb-src http://www.apache.org/dist/cassandra/debian 12x main
EOF

echo "*** Installing Cassandra"
apt-get update
apt-get install -y --force-yes cassandra procps wget
echo "*** Image build complete"
