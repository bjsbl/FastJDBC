# FastJDBC
a small and quick jdbc PoolDatasource,Integrate sql monitor

进行JDBC扩展，主要针对项目中监控SQL，所以开发这个中间件，目前还没有投入的生产中，期待更快捷的jdbc



public interface JdbcSqlBase {

	String getSql();

	String getName();

	Date getExecuteLastStartTime();
	
	Date getExecuteErrorLastTime();
	
	long getExecuteMillisTotal();

}
