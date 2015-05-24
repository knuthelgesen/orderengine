# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  
      config.vm.define :order1 do |order1|
        order1.vm.box = "precise64"
#        order1.vm.box_url ="http://jenkins.usrv.ubergenkom.no/boxes/precise64.box"
        order1.vm.hostname="order1"
#        order1.vm.synced_folder "intranett-statisk/statisk", "/u01/app/intranett/intranett-statisk/statisk", :owner => "vagrant", :mount_options => [ "dmode=775", "fmode=774" ]
#        order1.vm.synced_folder "sne-store", "/var/sne-store", :owner => "intranett", :mount_options => [ "dmode=775", "fmode=774" ], create: true
        order1.vm.network :forwarded_port,
             guest: 22,
             host: 2223,
             id: "ssh",
             auto_correct: true

        order1.vm.network :private_network, ip: "192.168.33.101"

        order1.vm.provider :virtualbox do |vb|
            vb.customize [
             "modifyvm", :id,
             "--name", "Order engine test server", 
             "--memory", "8192", 
             "--cpus", "4"
            ]  
        end
        order1.vm.provision :puppet do |puppet|
            puppet.manifests_path = "manifests"
            puppet.module_path = "modules"
            puppet.manifest_file  = "order1.pp"
        end
    end

  config.vm.define :order2 do |order2|
      
      order2.vm.box = "order"
#      order2.vm.box_url ="http://jenkins.usrv.ubergenkom.no/boxes/precise64.box"
      order2.vm.hostname="order2"
#      order2.vm.synced_folder "order-statisk/a01/statisk", "/a01/statisk", :owner => "vagrant", :mount_options => [ "dmode=775", "fmode=774" ]
      order2.vm.synced_folder "user-store/order", "/var/user-store/order", :owner => "order", :mount_options => [ "dmode=775", "fmode=774" ], create: true
#      order2.vm.synced_folder "skjema", "/var/skjema", :owner => "order", :mount_options => [ "dmode=775", "fmode=774" ], create: true
      order2.vm.network :forwarded_port,
              guest: 22,
              host: 2224,
              id: "ssh",
              auto_correct: true
      
      order2.vm.network :private_network, ip: "192.168.33.102"
      
      order2.vm.provider :virtualbox do |vb|
        vb.customize [
              "modifyvm", :id,
              "--name", "Order engine test server 2", 
              "--memory", "8196", 
              "--cpus", "4"
        ]  

      end
      order2.vm.provision :puppet do |puppet|
              puppet.manifests_path = "manifests"
              puppet.module_path = "modules"
              puppet.manifest_file  = "order2.pp"
      end
  end

    config.vm.define :order3 do |order3|

        order3.vm.box = "ubuntu/trusty64"
#        order3.vm.box_url ="http://jenkins.usrv.ubergenkom.no/boxes/precise64.box"
        order3.vm.hostname="order3"
        order3.vm.network :forwarded_port,
                guest: 22,
                host: 2225,
                id: "ssh",
                auto_correct: true

        order3.vm.network :private_network, ip: "192.168.33.103"

        order3.vm.provider :virtualbox do |vb|
          vb.customize [
                "modifyvm", :id,
             		"--name", "Order engine test server 3", 
                "--memory", "1024",
                "--cpus", "1"
          ]

        end
        order3.vm.provision :puppet do |puppet|
                puppet.manifests_path = "manifests"
                puppet.module_path = "modules"
                puppet.manifest_file  = "order3.pp"
        end
    end
  
end

