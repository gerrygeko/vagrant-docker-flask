#!/bin/sh
echo "----------------------PROVISIONING OF THE BOX----------------------"
sudo apt-get install -y python3-pip python3-setuptools &&
sudo apt-get autoclean -y &&
sudo apt-get clean -y &&
pip3 install wheel
echo "----------------------END OF THE PROVISIONING----------------------"