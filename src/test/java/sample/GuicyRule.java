package sample;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class GuicyRule implements MethodRule {

    private final Injector injector;

    public GuicyRule(Class... moduleClasses) {

        try {
            Module[] modules = new Module[moduleClasses.length];
            for (int i = 0; i < moduleClasses.length; i++) {
                Class module = moduleClasses[i];
                modules[i] = (Module) module.newInstance();
            }

            this.injector = Guice.createInjector(modules);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

                injector.injectMembers(target);
                base.evaluate();
            }
        };
    }
}
