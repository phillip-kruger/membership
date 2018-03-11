package com.github.phillipkruger.membership;

//import graphql.annotations.annotationTypes.GraphQLField;
//import graphql.annotations.annotationTypes.GraphQLName;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Member POJO
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
//@GraphQLName("person")
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@XmlRootElement @XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {
    private static final long serialVersionUID = -8531040143398373846L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GraphQLField
    private int id;
    
    @Column(name = "name")
    @ElementCollection(fetch = FetchType.EAGER,targetClass=String.class)
    //@GraphQLField
    private List<String> names = new LinkedList<>();
    
    //@GraphQLField
    private String surname;
 
    public void addName(String name){
        names.add(name);
    }
}
