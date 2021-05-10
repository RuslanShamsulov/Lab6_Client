import FormingObjects.FormingForAddCommand;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Main {
    private static SocketChannel socket;
    private static ByteBuffer byteBuffer;

    public static void main(String[] args) throws IOException {


        Scanner in2 = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите команду");
                String userInput = in2.nextLine();

                while (true) {
                    MessageForClient message = new MessageForClient();
                    if (userInput.contains("exit")) {
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        AbstractCommand<String> exit = new AbstractCommand<>("exit", "nothing");
                        message = send(exit);
                        System.out.println("Работа завершена командой exit");
                        break;
                    } else if (userInput.contains("help")) {
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        AbstractCommand<String> help = new AbstractCommand<>("help", "nothing");
                        message = send(help);
                    } else if (userInput.contains("info")) {
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        AbstractCommand<String> info = new AbstractCommand<>("info", "nothing");
                        message = send(info);

                    } else if (userInput.contains("show")) {
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        AbstractCommand<String> show = new AbstractCommand<>("show", "nothing");
                        message = send(show);


                    } else if (userInput.contains("add") && (!userInput.contains("max"))) {
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        FormingForAddCommand form4addcom = new FormingForAddCommand();
                        AbstractCommand<?> add = new AbstractCommand<>("add", form4addcom.form(in2));
                        message = send(add);


                    } else if (userInput.contains("remove_by_id")) { //тут все работает
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        AbstractCommand<String> remove_by_id = new AbstractCommand<>("remove_by_id", userInput);
                        message = send(remove_by_id);


                    } else if (userInput.contains("add_if_max")) { //работает
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        FormingForAddCommand form4addcom = new FormingForAddCommand();
                        AbstractCommand<?> add_if_max = new AbstractCommand<>("add_if_max", form4addcom.form(in2));
                        message = send(add_if_max);


                    } else if (userInput.contains("remove_greater")) {
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        // FormingForAddCommand form4addcom = new FormingForAddCommand();
                        AbstractCommand<?> remove_greater = new AbstractCommand<>("remove_greater", userInput);
                        message = send(remove_greater);

                    } else if (userInput.contains("remove_lower")) {
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        AbstractCommand<?> remove_lower = new AbstractCommand<>("remove_lower", userInput);
                        message = send(remove_lower);

                    } else if (userInput.contains("remove_any_by_engine_power")) {
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        AbstractCommand<String> remove_any_by_engine_power = new AbstractCommand<>("remove_any_by_engine_power", userInput);
                        message = send(remove_any_by_engine_power);

                    } else if (userInput.contains("min_by_creation_date")) {
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        AbstractCommand<String> min_by_creation_date = new AbstractCommand<>("min_by_creation_date", "nothing");
                        message = send(min_by_creation_date);

                    } else if (userInput.contains("clear")) {
                        SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
                        socket = SocketChannel.open(socketAddress);
                        AbstractCommand<String> clear = new AbstractCommand<>("clear", "nothing");
                        message = send(clear);

                    } else System.out.println("В пользовательском вводе не обнаружено команд\n" +
                            "Чтобы отобразить список возможных команд, введите help " +
                            "\nчтобы завершить действие программы, нажмите exit");

                    System.out.println("Введите команду");
                    userInput = in2.nextLine();

                }


            } catch (IllegalStateException e) {
                System.out.println("до свидания");
                break;
            } catch (ConnectException e) {
                System.out.println("Сервер не доступен");
                break;
            } catch (Exception e) {
                System.out.println("Произошла ошибка при обнаружении команды пользователя");
                e.printStackTrace();
                break;
            } finally {
                in2.close();
            }
        }
    }


    private static MessageForClient send(AbstractCommand<?> command) throws ClassNotFoundException {

        try {
            byteBuffer = ByteBuffer.allocate(65336);
            byteBuffer.put(serialize(command)); // в буфере теперь лежит сериализованный объект (поток)
            byteBuffer.flip();
            socket.write(byteBuffer);
            byteBuffer.clear();

            MessageForClient message = new MessageForClient();
            Socket socket = new Socket("localhost", 55554);
            ObjectOutputStream clientOutputStream = new
                    ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream clientInputStream = new
                    ObjectInputStream(socket.getInputStream());
            clientOutputStream.writeObject(message);
            System.out.println("команда отправлена");
            message = (MessageForClient) clientInputStream.readObject();
            System.out.println("Информация от сервера получена");
            System.out.println(message.getMessage());
            clientOutputStream.close();
            clientInputStream.close();
            socket.close();


            return message;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server is closed");
            return null;
        }
    }


    private static <T> byte[] serialize(T command) {  //объект в текст
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(command);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {

            System.out.println("Serialize problem");
            e.printStackTrace();
        }
        return null;
    }
}

