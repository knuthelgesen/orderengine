class sne::config {
    file {
        "/etc/sne":
            ensure => directory;
        "/etc/sne/oracle10g.create-schema.sql":
            owner => "vagrant", group => "vagrant",
            source => "puppet:///modules/sne/etc/sne/oracle10g.create-schema.sql";
        "/etc/sne/oracle10g.create.sql":
            owner => "vagrant", group => "vagrant",
            source => "puppet:///modules/sne/etc/sne/oracle10g.create.sql";
        "/etc/sne/oracle10g.drop.sql":
            owner => "vagrant", group => "vagrant",
            source => "puppet:///modules/sne/etc/sne/oracle10g.drop.sql";
    }
}