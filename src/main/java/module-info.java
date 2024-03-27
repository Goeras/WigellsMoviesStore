module org.dreamteam.wigellsmoviesstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires javafx.graphics;


    opens org.dreamteam.wigellsmoviesstore to javafx.fxml;
    exports org.dreamteam.wigellsmoviesstore;

}