class openldap {
    class { 'ldap::server':
        suffix => 'dc=bergen,dc=kommune,dc=no',
        rootdn => 'cn=orcladmin,dc=bergen,dc=kommune,dc=no',
        rootpw => 'portal88',
    }
}