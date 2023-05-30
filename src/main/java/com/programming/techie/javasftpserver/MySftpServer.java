package com.programming.techie.javasftpserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.config.keys.AuthorizedKeysAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.subsystem.sftp.SftpSubsystemFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.server.Command;

@Service
public class MySftpServer {

    private Log log = LogFactory.getLog(MySftpServer.class);

    @PostConstruct
    public void startServer() throws IOException {
        start();
    }

    private void start() throws IOException {
        SshServer sshd = SshServer.setUpDefaultServer();
        sshd.setHost("0.0.0.0");
        sshd.setPort(2222);
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(new File("host.ser")));
        sshd.setSubsystemFactories(Collections.singletonList(new SftpSubsystemFactory()));
        sshd.setPasswordAuthenticator((username, password, session) -> username.equals("test") && password.equals("password"));
        sshd.setPublickeyAuthenticator(new AuthorizedKeysAuthenticator(new File("C:\\3Projects\\lma-sftp-svc\\files")));
        
        Path dir = Paths.get("/tmp");
        Files.createDirectories(dir);
        sshd.setFileSystemFactory(new VirtualFileSystemFactory(dir.toAbsolutePath()));
        
        sshd.start();
        log.info("SFTP server started");
    }

//    public void startSFTPServer() throws IOException {
//        SshServer sshd = SshServer.setUpDefaultServer();
//        sshd.setHost("localhost");
//         sshd.setPort(2222);
//        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
//        //Accept all keys for authentication
//        sshd.setPublickeyAuthenticator((s, publicKey, serverSession) -> true);
//        //Allow username/password authentication using pre-defined credentials
//        sshd.setPasswordAuthenticator((username, password, serverSession) -> this.username.equals(username) && this.password.equals(password));
//        //Setup Virtual File System (VFS)
//        //Ensure VFS folder exists
//        Path dir = Paths.get("C:\\3Projects\\java-sftpserver");
//        Files.createDirectories(dir);
//        sshd.setFileSystemFactory(new VirtualFileSystemFactory(dir.toAbsolutePath()));
//        //Add SFTP support
//        List<NamedFactory<Command>> sftpCommandFactory = new ArrayList<>();
//        sftpCommandFactory.add(new SftpSubsystemFactory());
//        sshd.setSubsystemFactories(sftpCommandFactory);
//        sshd.start();
//    }

}
