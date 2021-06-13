package com.world.meet.w2m.utils;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileUtils
{
	public void writeLog(String data)
	{

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			File file = new File(getPath());
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(data);

		} catch (IOException e) {
			System.out.println("error durante la optecion del archivo");
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				System.out.println("error al cerrarn las");
			}
		}

	}

	private String getPath(){
		return "/home/juancarlosaguilar/Desktop/java-11/w2m/src/main/resources/logs/metric.txt";
	}
}
