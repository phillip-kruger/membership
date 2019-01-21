package com.github.phillipkruger.membership;

import io.leangen.graphql.annotations.GraphQLId;
import io.leangen.graphql.annotations.GraphQLQuery;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Schema(name="Person", description="POJO that represents a person.")
@NamedQueries({
    @NamedQuery(name = Person.QUERY_FIND_ALL, query = "SELECT n FROM Person n")
})
public class Person implements Serializable {
    private static final long serialVersionUID = -8531040143398373846L;
    public static final String QUERY_FIND_ALL = "Person.findAll";
    
    @GraphQLQuery
    @Id @GraphQLId @Schema(required = true, example = "1", description = "Unique identifier")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name")
    @ElementCollection(fetch = FetchType.EAGER,targetClass=String.class)
    @GraphQLQuery 
    @Size(min=1,max=4, message = "Name can not be empty")
    @Schema(required = true, description = "All the names of this person", example = "Natus,Phillip")
    private List<String> names;
    
    @GraphQLQuery 
    @NotNull(message = "Surname can not be empty") 
    @Size(min=2, message = "Surname '${validatedValue}' is too short, minimum {min} characters")
    @Schema(required = true, description = "The surname of this person", example = "Kruger")
    private String surname;
 
    public void addName(String name){
        if(names==null)names = new LinkedList<>();
        names.add(name);
    }
}
