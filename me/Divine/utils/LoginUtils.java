package me.Divine.utils;

import java.net.*;
import com.mojang.authlib.yggdrasil.*;
import com.mojang.authlib.*;
import net.minecraft.client.*;
import me.Divine.utils.system.*;
import me.Divine.wrappers.*;
import net.minecraft.util.*;
import com.mojang.authlib.exceptions.*;
import java.lang.reflect.*;

public class LoginUtils
{
    public static String loginAlt(final String email, final String password) {
        final YggdrasilAuthenticationService authenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        final YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication)authenticationService.createUserAuthentication(Agent.MINECRAFT);
        authentication.setUsername(email);
        authentication.setPassword(password);
        String displayText = null;
        try {
            authentication.logIn();
            try {
                final Field f = Minecraft.class.getDeclaredField(Mapping.session);
                f.setAccessible(true);
                f.set(Wrapper.INSTANCE.mc(), new Session(authentication.getSelectedProfile().getName(), authentication.getSelectedProfile().getId().toString(), authentication.getAuthenticatedToken(), "mojang"));
                displayText = "Logged [License]: " + Wrapper.INSTANCE.mc().func_110432_I().func_111285_a();
            }
            catch (Exception e) {
                displayText = "Unknown error.";
                e.printStackTrace();
            }
        }
        catch (AuthenticationUnavailableException e3) {
            displayText = "Cannot contact authentication server!";
        }
        catch (AuthenticationException e2) {
            if (e2.getMessage().contains("Invalid username or password.") || e2.getMessage().toLowerCase().contains("account migrated")) {
                displayText = "Wrong password!";
            }
            else {
                displayText = "Cannot contact authentication server!";
            }
        }
        catch (NullPointerException e4) {
            displayText = "Wrong password!";
        }
        return displayText;
    }
    
    public static String getName(final String email, final String password) {
        final YggdrasilAuthenticationService authenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        final YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication)authenticationService.createUserAuthentication(Agent.MINECRAFT);
        authentication.setUsername(email);
        authentication.setPassword(password);
        try {
            authentication.logIn();
            return authentication.getSelectedProfile().getName();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static void changeCrackedName(final String name) {
        try {
            final Field f = Minecraft.class.getDeclaredField(Mapping.session);
            f.setAccessible(true);
            f.set(Wrapper.INSTANCE.mc(), new Session(name, "", "", "mojang"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
