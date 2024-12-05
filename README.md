# Read Me

The question is from  
https://stackoverflow.com/questions/79044103/how-to-use-datasource-bean-when-calling-hazelcast-sources-jdbc

When we are running a Jet job that requires a Connection, we can get it from a Spring DataSource.
For this purpose we need to have a DataSourceProvider bean that derives from ApplicationContextAware.

Then DataSourceProvider can access org.springframework.context.ApplicationContext and get a DataSource

