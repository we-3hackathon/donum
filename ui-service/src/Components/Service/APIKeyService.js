import axios from "axios";


export default class APIKeyService{

	constructor(){
		this.key = "not_found";
	}

    static fetchApiKey() {
				
		axios
			.get("http://18.130.137.35:5333/get-key/google/map")
			.then(result => {
			  if(result.status = 200){
				  this.key = result.data;
				  console.log(this.key);
			  }
			});  
			
		return this.key;
	}
	
}