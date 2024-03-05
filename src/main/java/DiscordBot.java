import events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.File;
import java.io.FileNotFoundException;

public class DiscordBot {
    public static void main(String[] args) throws FileNotFoundException {
        final String TOKEN = "TOKEN";
        JDABuilder jdaBuilder = JDABuilder.createDefault(TOKEN);
        JDA jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new ReadyEventListener(), new MessageEventListener(), new InteractionEventListener())
                .build();
        jda.upsertCommand("q", "Say Hello").setGuildOnly(true).queue();
        jda.upsertCommand("joke", "300").setGuildOnly(true).queue();
        jda.upsertCommand("rndbuild", "Random dota 2 item build").setGuildOnly(true).queue();
    }
}
