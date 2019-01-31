# -*- mode: ruby -*-
# vi: set ft=ruby :
Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.provider "virtualbox" do |vb|
    vb.memory = 2048
    vb.cpus = 2
  end

  # Installing Docker
  config.vm.provision "shell", inline: <<-SHELL
    wget -qO- https://get.docker.com/ | sh
  SHELL

  vm_name = "vagrant-environment"
  config.vm.define vm_name do |host|
    host.vm.synced_folder "webapp/", "/opt/webapp"

    # Add this ip to you hosts file with his own hostname
    host.vm.network :private_network, :ip => '192.168.59.100'
  end

  # Script to provision the VagrantBox with stuff (Python3 for now)
  config.vm.provision "shell", path: "bootstrap.sh", privileged: false

end