package uk.ac.ucl.model;

import java.io.File;
import java.io.IOException;

public class ModelFactory
{
  private static Model model;

  public static Model getModel() throws IOException
  {
    if (model == null)
    {
      String fileSeparator = File.separator;
      model = new Model();
      model.readFile(new File("."+fileSeparator+"data"+fileSeparator+"data.json"));
    }
    return model;
  }
}
