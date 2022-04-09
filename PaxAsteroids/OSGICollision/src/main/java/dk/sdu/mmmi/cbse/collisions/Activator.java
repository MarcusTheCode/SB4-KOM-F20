package dk.sdu.mmmi.cbse.collisions;

import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {


    @Override
    public void start(BundleContext bundleContext) throws Exception {
        CollisionDetector cl = new CollisionDetector();
        bundleContext.registerService(IPostEntityProcessingService.class, cl, null);

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
