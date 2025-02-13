package twilightforest.client;

import com.google.common.base.Joiner;
import net.minecraft.server.packs.AbstractPackResources;
import net.minecraft.server.packs.ResourcePackFileNotFoundException;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import net.minecraftforge.forgespi.locating.IModFile;
import twilightforest.TwilightForestMod;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public class TwilightLegacyPack extends AbstractPackResources {
    private final IModFile modFile;
    private static final String subDir = "classic/";

    public TwilightLegacyPack(IModFile modFile) {
        super(modFile.getFilePath().toFile());
        this.modFile = modFile;
    }

    // Forgecopy ModFileResourcePack#getResourceNamespaces: added `subDir + `
    @Override
    public Set<String> getNamespaces(PackType type) {
        try {
            Path root = modFile.findResource(subDir + type.getDirectory()).toAbsolutePath();

            return Files.walk(root,1)
                    .map(path -> root.relativize(path.toAbsolutePath()))
                    .filter(path -> path.getNameCount() > 0) // skip the root entry
                    .map(p->p.toString().replaceAll("/$","")) // remove the trailing slash, if present
                    .filter(s -> !s.isEmpty()) //filter empty strings, otherwise empty strings default to minecraft in ResourceLocations
                    .collect(Collectors.toSet());
        }
        catch (Throwable t) {
            TwilightForestMod.LOGGER.error("TwilightLegacyPack failed to collect resource namespaces!", t);

            return Collections.emptySet();
        }
    }

    // Forgecopy ModFileResourcePack#getInputStream: added `subDir + `
    @Override
    protected InputStream getResource(String location) throws IOException {
        final Path path = modFile.findResource(subDir + location);

        if (!Files.exists(path)) {
            TwilightForestMod.LOGGER.error("File does not exist!");
            throw new ResourcePackFileNotFoundException(path.toFile(), location);
        }

        return Files.newInputStream(path, StandardOpenOption.READ);
    }

    // Forgecopy ModFileResourcePack#resourceExists: added `subDir + `
    @Override
    protected boolean hasResource(String resourcePath) {
        return Files.exists(modFile.findResource(subDir + resourcePath));
    }

    // Forgecopy ModFileResourcePack#getAllResourceLocations: added `subDir + `
    @Override
    public Collection<ResourceLocation> getResources(PackType type, String namespaceIn, String pathIn, int maxDepthIn, Predicate<String> filterIn) {
        try {
            Path root = modFile.findResource(subDir + type.getDirectory()).toAbsolutePath();
            Path inputPath = root.getFileSystem().getPath(pathIn);

            return Files.walk(root).
                    map(path -> root.relativize(path.toAbsolutePath())).
                    filter(path -> path.getNameCount() > 1 && path.getNameCount() - 1 <= maxDepthIn). // Make sure the depth is within bounds, ignoring domain
                    filter(path -> !path.toString().endsWith(".mcmeta")). // Ignore .mcmeta files
                    filter(path -> path.subpath(1, path.getNameCount()).startsWith(inputPath)). // Make sure the target path is inside this one (again ignoring domain)
                    filter(path -> filterIn.test(path.getFileName().toString())). // Test the file name against the predicate
                    // Finally we need to form the RL, so use the first name as the domain, and the rest as the path
                    // It is VERY IMPORTANT that we do not rely on Path.toString as this is inconsistent between operating systems
                    // Join the path names ourselves to force forward slashes
                            map(path -> new ResourceLocation(path.getName(0).toString(), Joiner.on('/').join(path.subpath(1, Math.min(maxDepthIn, path.getNameCount()))))).
                            collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void close() {
        // Forge's ModFileResourcePack noop's here, so I assume we can do the same too
    }

    @Override
    public String getName() {
        return "Twilight Classic";
    }

    // VanillacopyL ResourcePack#getMetadata
    @Nullable
    @Override
    public <T> T getMetadataSection(MetadataSectionSerializer<T> serializer) throws IOException {

        InputStream inputStream = getResource("pack.mcmeta");

        Throwable throwable = null;

        T resourceMetaData;
        try {
            resourceMetaData = getMetadataFromStream(serializer, inputStream);
        } catch (Throwable t) {
            throwable = t;
            throw t;
        } finally {
            if (inputStream != null) {
                if (throwable != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable t) {
                        throwable.addSuppressed(t);
                    }
                } else {
                    inputStream.close();
                }
            }
        }

        return resourceMetaData;
    }
}
