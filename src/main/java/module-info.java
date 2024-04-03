module org.dreamteam.wigellsmoviesstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires javafx.graphics;
    requires org.hibernate.orm.spatial;
    requires org.locationtech.jts;


    opens org.dreamteam.wigellsmoviesstore.Entitys;
    opens org.dreamteam.wigellsmoviesstore to javafx.fxml;
    exports org.dreamteam.wigellsmoviesstore;
    exports org.dreamteam.wigellsmoviesstore.Entitys;
    exports org.dreamteam.wigellsmoviesstore.Controllers;
    opens org.dreamteam.wigellsmoviesstore.Controllers to javafx.fxml;

}