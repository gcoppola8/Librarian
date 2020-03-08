module dev.coppola.librarian {
	exports dev.coppola.librarian.core;

	requires lombok;
	requires org.joda.money;
	requires slf4j.api;
	requires com.zaxxer.hikari;
	requires java.sql;
}