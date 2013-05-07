class UrlMappings {
    static mappings = {
        "/jpslab/news/$year/$month/$day/$newsLink"{
        	  controller='jpslab'
        	  action='index'
         }	
       
        
        "/jpslab/creations/$year/$month/$day/$creationLink"{
      	  controller='jpslab'
      	  action='creations'
        }	
      	
        "/$controller/$action?/$id?"{
  	      constraints {
  			 id(matches:/d/)
  		  }
  	  }
        "/"{
      	  controller='jpslab'
      	  action='index'//(view:"/index")
        }
        
	  "500"(view:'/error')
	  "404"(view:'/error')
	}
}
