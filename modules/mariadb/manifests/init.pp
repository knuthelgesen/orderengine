class mariadb::server($mysql_password) {
  package { "mariadb-server": ensure => installed }
    service { "mysql":
    enable => true,
    ensure => running,
    require => Package["mariadb-server"],
  }

  package { "mariadb-client":
    ensure => present,
  }

  exec { "set-mysql-password":
    unless => "/usr/bin/mysqladmin -uroot -p${mysql_password} status",
    command => "/usr/bin/mysqladmin -uroot password ${mysql_password}",
    require => Service["mysql"],
  }
}

#define mysqldb( $user, $password ) {
#  exec { "create-${name}-db":
#    unless => "/usr/bin/mysql -uroot -p${mysql_password} ${name}",
#    command => "/usr/bin/mysql -uroot -p${mysql_password} -e \"create database ${name};\"",
#    require => Service["mysql"],
#  }

#  exec { "grant-${name}-db":
#    unless => "/usr/bin/mysql -u${user} -p${password} ${name}",
#    command => "/usr/bin/mysql -uroot -p${mysql_password} -e \"grant all on ${name}.* to ${user}@'%' identified by '${password}'; grant all on ${name}.* to ${user}@'localhost' identified by #'${password}';\"",
#    require => [Service["mysql"], Exec["create-${name}-db"]]
#  }

#  exec { "clear-default-users-${name}":
#    onlyif => "/usr/bin/mysql -uroot -psecret -e \"SELECT EXISTS(SELECT 0 FROM mysql.user WHERE user = '')\" | grep 1",
#    command => "/usr/bin/mysql -uroot -p${mysql_password} -e \" drop user ''@'localhost'; drop user ''@'intranett1'\"",
#    require => [Service["mysql"], Exec["create-${name}-db"], Exec["grant-${name}-db"]]
#  }
#}
