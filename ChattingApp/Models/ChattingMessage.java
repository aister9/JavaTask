package ChattingApp.Models;

import java.io.Serializable;

public class ChattingMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public enum Sender{
		ME{
			@Override public String toString() {return "나";}
		}, OTHER{
			@Override public String toString() {return "다른 사용자";}
		}
	}
	private Sender sender = Sender.ME;
	private String content;
	public ChattingMessage() {
	}
	public ChattingMessage(String content) {
		this.content = content;
	}
	public ChattingMessage(Sender sender, String content) {
		this.sender = sender;
		this.content = content;
	}
	public ChattingMessage(ChattingMessage other) {
		this.sender = other.sender;
		this.content = other.content;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s: %s", sender, content);
	}		
}
