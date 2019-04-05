package example.com.myapplication.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApplication {
}
