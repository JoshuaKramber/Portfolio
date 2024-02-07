import java.util.ArrayList;

//test class to make sure BFS and DFS were working on a preset tree
//goes hand in hand with the alpha class
public class TestTree {
    public static void main(String[]args){
        Alpha a = new Alpha("A");
        Alpha b = new Alpha("B");
        Alpha c = new Alpha("C");
        Alpha d = new Alpha("D");
        Alpha e = new Alpha("E");
        Alpha f = new Alpha("F");
        Alpha g = new Alpha("G");
        Alpha h = new Alpha("H");
        Alpha i = new Alpha("I");

        Node<Alpha> root = new Node<Alpha>(a);
        Node<Alpha> b2 = new Node<Alpha>(b);
        Node<Alpha> c2 = new Node<Alpha>(c);
        Node<Alpha> d2 = new Node<Alpha>(d);
        Node<Alpha> e2 = new Node<Alpha>(e);
        Node<Alpha> f2 = new Node<Alpha>(f);
        Node<Alpha> g2 = new Node<Alpha>(g);
        Node<Alpha> h2 = new Node<Alpha>(h);
        Node<Alpha> i2 = new Node<Alpha>(i);

        root.addChild(b2);
        root.addChild(c2);
        root.addChild(d2);
        b2.addChild(e2);
        b2.addChild(f2);
        d2.addChild(g2);
        d2.addChild(h2);
        d2.addChild(i2);

        

        //push root
        //while condition is while(queue/stack is not empty)
        //pop then push all of the popped nodes children
        
        
        Queue<Integer> q = new Queue<Integer>();
        /*
        q.push(root);
        while(!q.isEmpty()){
            Node<Alpha> test = q.pop();
            if(test!=null){
                System.out.print(test.value().alpha()+" ");
                for(int i3 = 0;i3<test.children().size();i3++){
                    //System.out.print(test.children().get(i3).value().alpha());
                    q.push(test.children().get(i3));
                }
            }
        }
        */
        
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);
        q.pop();
        q.pop();
        q.push(5);
        q.push(6);
        q.push(7);
        q.push(8);
        q.pop();
        q.pop();
        q.pop();
        q.pop();
        q.push(9);
        q.push(10);
        q.push(11);
        q.push(12);
        q.push(13);
        q.push(14);
        q.push(15);
        q.push(16);
        q.push(17);
        System.out.println(q.toString());

    }


}
