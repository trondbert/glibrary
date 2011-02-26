dataSource {
    pooled = true
    driverClassName = "oracle.jdbc.driver.OracleDriver"		
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
			url = "jdbc:oracle:thin:@localhost:1521:xe"	
			username = "grailsDev"
			password = "grailsDev"			
        }
    }
    test {
        dataSource {
            dbCreate = "create-drop"            
			url = "jdbc:oracle:thin:@localhost:1521:xe"
			username = "grailsTest"    
			password = "grailsTest"
        }
    }
    production {
        dataSource {
            dbCreate = "update"            
			url = "jdbc:oracle:thin:@localhost:1521:xe"
			username = "grailsProd"    
			password = "grailsProd"
        }
    }
}
