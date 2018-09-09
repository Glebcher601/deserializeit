package com.nixsolutions.deserializeit;

import org.apache.commons.io.serialization.ValidatingObjectInputStream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//"C:\\Users\\Gleb\\Desktop\\YSO\\payloadTEST.bin"
public class MainManualDeserialization
{
  private static final String BASIC = "basic";
  private static final String VALIDATED = "validated";

  public static void main(String args[]) throws Exception
  {

    if (args.length < 2)
    {
      throw new IllegalStateException();
    }

    Path path = Paths.get(args[1]);
    byte[] data = Files.readAllBytes(path);
    InputStream d = new ByteArrayInputStream(data);
    ObjectInputStream ois = new ObjectInputStream(d);

    if (args[0].equals(BASIC))
    {
      ois.readObject();
    }
    else if (args[0].equals(VALIDATED))
    {
      ValidatingObjectInputStream vois = new ValidatingObjectInputStream(ois);
      vois.accept(String.class);
      vois.readObject();
    }

  }
}
