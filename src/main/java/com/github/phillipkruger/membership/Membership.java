package com.github.phillipkruger.membership;

import io.leangen.graphql.annotations.GraphQLId;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@XmlRootElement @XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = Membership.QUERY_FIND_ALL, query = "SELECT m FROM Membership m"),
    @NamedQuery(name = Membership.QUERY_FIND_ALL_TYPE, query = "SELECT m FROM Membership m WHERE m.type=:type")
})
@Schema(name="Membership", description="POJO that represents a membership of a person.")
public class Membership implements Serializable {
    private static final long serialVersionUID = -8531040143398373846L;

    public static final String QUERY_FIND_ALL = "Membership.findAll";
    public static final String QUERY_FIND_ALL_TYPE = "Membership.findAllType";
    
    @GraphQLQuery
    @Id @GraphQLId @Schema(required = true, example = "1", description = "Unique identifier")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int membershipId;
    
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    @GraphQLQuery 
    @NotNull(message = "Owner can not be empty")
    @Schema(required = true, description = "The person that owns this membership")
    private Person owner;
    
    @GraphQLQuery 
    @NotNull(message = "Type can not be empty")
    @Column(name = "membership_type")
    @Schema(required = true, description = "The type of membership")
    private Type type;
    
}
