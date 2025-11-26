package ChattingApp.Models;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChattingLog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<ChattingMessage> log = new ArrayList<>();
	
	public List<ChattingMessage> getList(){return log;}
	public void add(ChattingMessage newMsg) {
		log.add(newMsg);
	}
	
	public void writeLog(String path) throws IOException{
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)))){
			oos.writeObject(this);
		}
	}
		
	public ChattingLog() {};
	public ChattingLog(String path) throws IOException, ClassNotFoundException{
		ChattingLog loaded = readLog(path);
		this.log = new ArrayList<>(loaded.log);
	}
	
	public boolean isEmpty() {
		return log.isEmpty();
	}
	
	public static ChattingLog readLog(String path) throws IOException, ClassNotFoundException{
		try(ObjectInputStream oos = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)))){
			return (ChattingLog) oos.readObject();}
	}
}
