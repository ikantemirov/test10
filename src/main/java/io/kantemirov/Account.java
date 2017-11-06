package io.kantemirov;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:account.properties")
public interface Account extends Config {
    @Key("username")
    String username();
    @Key("psw")
    String psw();
}
