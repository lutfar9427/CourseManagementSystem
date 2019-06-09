/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceCode;

import com.healthmarketscience.jackcess.CryptCodecProvider;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import java.io.File;
import java.io.IOException;
import net.ucanaccess.jdbc.JackcessOpenerInterface;

/**
 *
 * @author Hak Srun Lao
 */
public class OpenDatabase implements JackcessOpenerInterface {
    @Override
    public Database open(File file, String password) throws IOException {
        DatabaseBuilder databaseFile = new DatabaseBuilder(file);
        databaseFile.setAutoSync(false);
        databaseFile.setCodecProvider(new CryptCodecProvider(password));
        databaseFile.setReadOnly(false);
        return databaseFile.open();
    }
}
