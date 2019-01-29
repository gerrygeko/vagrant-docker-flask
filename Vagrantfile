# -*- mode: ruby -*-
# vi: set ft=ruby :
Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.network "private_network", ip: "192.168.10.101"
  config.vm.provider "virtualbox" do |vb|
    vb.memory = 2048
    vb.cpus = 2
  end

  # Script to provision the VagrantBox with stuff (Python3 for now)
  config.vm.provision "shell", path: "bootstrap.sh", privileged: false

  # Installing Docker
  config.vm.provision "shell", inline: <<-SHELL
    wget -qO- https://get.docker.com/ | sh
  SHELL

end