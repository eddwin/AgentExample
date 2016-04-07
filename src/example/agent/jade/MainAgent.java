package example.agent.jade;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class MainAgent extends Agent {
	
	protected void setup(){
		
		System.out.println("Hello! Buyer-agent "+getAID().getName()+" is ready.");
		
		//Add Behavior
		
		addBehaviour(new PriceRequestServer());
	}
	
	/*
	 * Inner class PriceRequestServer
	 * Serves proper requests with the current price
	 */
	
	private class PriceRequestServer extends CyclicBehaviour {

		public void action() {
			//Accept only messages with REQUEST performative (FIPA)
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
			ACLMessage msg = myAgent.receive(mt);
			if (msg!=null){
				//Message received
				String title = msg.getContent();
				ACLMessage reply = msg.createReply();
				
				//Fixed price (it's an example, come on) 
				Integer price = 10;
				
				//Reply with the price
				reply.setPerformative(ACLMessage.INFORM);
				reply.setContent(String.valueOf(price.intValue()));	
			}else {
				block();
			}
		}
	} //End of inner class PriceRequestServer
}
