package org.smatmenu.DB;

import org.glassfish.jersey.model.internal.RankedComparator.Order;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.smartmenu.common.ParameterCatalog;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MorphiaService
{

	
	public MorphiaService(String restaurantId)
	{		 
		// we use MongoClient to connect the local host (127.0.0.1)
		// (assuming this is where your mongodb instance is running) 
		// on port 27017 (the default port)
		MongoClient mongoClient = new MongoClient(ParameterCatalog.DATA_BASE_IP);		
		
		
		//create a new morphia instance
		this.morphia = new Morphia(); 
		String databaseName = ParameterCatalog.DATA_BASE_NAME;
		this.datastore = morphia.createDatastore(mongoClient, databaseName+"-"+restaurantId);
		
		
	
	}
	 
		private Morphia morphia;
		private Datastore datastore;
		
		

	 
		
		public Morphia getMorphia() {
			return morphia;
		}
	 
		public void setMorphia(Morphia morphia) {
			this.morphia = morphia;
		}
	 
		public Datastore getDatastore() {
			return datastore;
		}
	 
		public void setDatastore(Datastore datastore) {
			this.datastore = datastore;
		}
		
	}
	


