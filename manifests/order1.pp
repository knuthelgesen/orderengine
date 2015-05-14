import "apt.pp"

class order1 {

  class { 'config':
    my_ip => '192.168.33.101',
    other_ip => '192.168.33.102',
    constretto_tags => 'ITEST'
  }

}

include ordernode
include order1
