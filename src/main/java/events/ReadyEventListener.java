package events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.IEventManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReadyEventListener implements EventListener {

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (genericEvent instanceof ReadyEvent) {
            System.out.println("The bot is ready and online!");
        }
    }
}
