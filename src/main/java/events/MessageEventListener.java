package events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageEventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        System.out.println(event.getMessage().getAuthor().getName() + " said: " + event.getMessage().getContentRaw());

        toxicityCheck(event);
    }

    public void toxicityCheck(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().contains("бот ")) {
            event.getMessage().reply("сам ты " + event.getMessage().getContentRaw().replace("бот ", "")).queue();
        }
    }
}
