package org.dreamteam.wigellsmoviesstore.Entitys;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.sql.Timestamp;

//TODO obs, i denna klass vill Geppe använda Set, istället för list. Så kan bli strul pga det
/*

I Hibernate-entiteter är det vanligt att använda Set för att representera relationer till andra entiteter, särskilt när det gäller @OneToMany-relationer. Detta beror på följande skäl:

Unika poster: Eftersom ett Set inte tillåter dupliceringar, ser Hibernate till att endast unika poster lagras. Detta kan vara användbart för att undvika dubbletter i relationen.

Effektivitet: När du arbetar med en Set, krävs det inte att Hibernate laddar hela uppsättningen objekt i minnet innan den kan avgöra om en entitet redan finns i samlingen. Detta kan förbättra prestandan jämfört med att använda en List, särskilt när det finns många objekt i relationen.

Semantik: Eftersom Set representerar en uppsättning entiteter, förmedlar det tydligt att ordningen inte är viktig och att varje entitet endast ska förekomma en gång. Detta kan vara mer lämpligt för vissa typer av relationer.

Med det sagt är det tekniskt sett möjligt att använda en List istället för en Set för @OneToMany-relationer i Hibernate-entiteter. Det kan vara lämpligt om du behöver behålla ordningen på objekten i samlingen eller om dupliceringar är tillåtna och meningsfulla för din användning.
 */

@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", length = 20)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @OneToMany(mappedBy = "language")
    private List<Film> films = new ArrayList<>();

    @OneToMany(mappedBy = "originalLanguage")
    private List<Film> originalLanguageFilms = new ArrayList<>();

    /*  Orginalförslaget av Geppe. Kanske ska vara Set pga nycklarna?

        @OneToMany(mappedBy = "language")
    private Set<Film> films;

    @OneToMany(mappedBy = "originalLanguage")
    private Set<Film> originalLanguageFilms;
     */

    public Language() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public List<Film> getOriginalLanguageFilms() {
        return originalLanguageFilms;
    }

    public void setOriginalLanguageFilms(List<Film> originalLanguageFilms) {
        this.originalLanguageFilms = originalLanguageFilms;
    }
}
