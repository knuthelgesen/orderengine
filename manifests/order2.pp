import "apt.pp"

class order2 {

  class { 'config':
    my_ip => '192.168.33.102',
    other_ip => '192.168.33.101',
    constretto_tags => 'ITEST'
  }

}

include ordernode
include order2
