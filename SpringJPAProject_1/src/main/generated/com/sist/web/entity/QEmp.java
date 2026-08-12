package com.sist.web.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmp is a Querydsl query type for Emp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmp extends EntityPathBase<Emp> {

    private static final long serialVersionUID = 251669153L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmp emp = new QEmp("emp");

    public final NumberPath<Integer> comm = createNumber("comm", Integer.class);

    public final QDept dept;

    public final NumberPath<Integer> empno = createNumber("empno", Integer.class);

    public final StringPath ename = createString("ename");

    public final DateTimePath<java.util.Date> hiredate = createDateTime("hiredate", java.util.Date.class);

    public final StringPath job = createString("job");

    public final NumberPath<Integer> mgr = createNumber("mgr", Integer.class);

    public final NumberPath<Integer> sal = createNumber("sal", Integer.class);

    public QEmp(String variable) {
        this(Emp.class, forVariable(variable), INITS);
    }

    public QEmp(Path<? extends Emp> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmp(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmp(PathMetadata metadata, PathInits inits) {
        this(Emp.class, metadata, inits);
    }

    public QEmp(Class<? extends Emp> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dept = inits.isInitialized("dept") ? new QDept(forProperty("dept")) : null;
    }

}

