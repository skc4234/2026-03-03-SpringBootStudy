package com.sist.web.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDept is a Querydsl query type for Dept
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDept extends EntityPathBase<Dept> {

    private static final long serialVersionUID = -788228212L;

    public static final QDept dept = new QDept("dept");

    public final NumberPath<Integer> deptno = createNumber("deptno", Integer.class);

    public final StringPath dname = createString("dname");

    public final StringPath loc = createString("loc");

    public QDept(String variable) {
        super(Dept.class, forVariable(variable));
    }

    public QDept(Path<? extends Dept> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDept(PathMetadata metadata) {
        super(Dept.class, metadata);
    }

}

