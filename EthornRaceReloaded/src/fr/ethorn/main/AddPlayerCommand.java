package fr.ethorn.main;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class AddPlayerCommand implements CommandExecutor{



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("ethorn")){
            if(Objects.equals(strings[0], "player")){
                if(strings[1] != null){
                    if(Objects.equals(strings[2], "add")){
                        if(strings[3] != null ){
                            String playerName = strings[1];
                            String rank = strings[3];
                            try {
                                PreparedStatement pS4 = Main.instance.SQL.getConnection().prepareStatement("UPDATE `user` SET `id_classe`= ? WHERE (`playername`= ? )");
                                pS4.setString(1, rank);
                                pS4.setString(2, playerName);
                                pS4.execute();
                                pS4.close();
                            } catch (SQLException e3) {
                                e3.printStackTrace();
                            }

                        }
                    }
                }
            }
        }
            return false;
    }
}
