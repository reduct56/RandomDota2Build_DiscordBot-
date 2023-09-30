package events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class InteractionEventListener extends ListenerAdapter {

    HashMap<Integer, String> items = new HashMap<>();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "q" -> event.reply("Шалом, " + event.getUser().getName()).queue();
            case "joke" -> {
                try {
                    event.reply(read(
                            new File("C:\\Users\\slava\\Desktop\\progr\\DiscordBot\\JavaDiscordBot\\src\\main\\java\\events\\Jokes.txt"))).queue();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "rndbuild" -> {
                try {
                    getRandomItem();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    event.reply(chooseItems()).queue();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(event.getUser().getName() + " issued a command /" + event.getName());
    }

    public String read(File file) throws IOException {
        Scanner myReader = new Scanner(file);
        List<String> dataList = new ArrayList<>();
        StringBuilder dataCurrent = new StringBuilder();
        while (myReader.hasNextLine()) {
            String tmp = myReader.nextLine();
            if (!tmp.equals("%")) {
                dataCurrent.append(tmp).append("\n");
            }
            else {
                dataList.add(dataCurrent.toString());
                dataCurrent = new StringBuilder();
            }
        }
        return dataList.get(new Random().nextInt(0, dataList.size()));
    }

    public void readItems() throws IOException {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\slava\\Desktop\\progr\\DiscordBot\\JavaDiscordBot\\src\\main\\resources\\items.json")));

        JSONArray array = new JSONArray(data);
        for (int i = 0; i < array.length(); i++) {
            String str = array.get(i).toString();
            JSONObject object = new JSONObject(str);

            String item = object.getString("name");
            int id = object.getInt("id");
            items.put(id, item);
        }
    }

    private String getRandomItem() throws IOException {
        readItems();
        Random rnd = new Random();
        return items.get(rnd.nextInt(items.size()));
    }

    private String chooseItems() throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> usedItems = new ArrayList<>();
        ArrayList<String> multiItems = new ArrayList<>();
        multiItems.add("moon shard");
        multiItems.add("heart of tarrasque");
        multiItems.add("butterfly");
        multiItems.add("daedalus");
        multiItems.add("divine rapier");
        multiItems.add("eye of skadi");
        sb.append("Random itembuild:");
        for (int i = 0; i < 6; i++) {
            String currentItem = getRandomItem();
            if (usedItems.contains(currentItem) && !multiItems.contains(currentItem)) {
                currentItem = getRandomItem();
            }
            sb.append("\n").append(i+1).append(". ").append(currentItem);
            usedItems.add(currentItem);
        }
        return sb.toString();
    }
}
