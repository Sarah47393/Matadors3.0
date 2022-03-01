<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\UserType;
use App\Form\UserFrontType;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;
/**
 * @Route("/user")
 */
class UserController extends AbstractController
{
    
    /**
     * @Route("/", name="user_index", methods={"GET"})
     */
    public function index(UserRepository $userRepository): Response
    {
        return $this->render('user/index.html.twig', [
            'users' => $userRepository->findAll(),
        ]);
    }
   
     /**
     * @Route("/AllUsers", name="AllUsers")
     */
public function AllUsers(NormalizerInterface $Normalizer )
{
//Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
$repository =$this->getDoctrine()->getRepository(User::class);
$users=$repository->FindAll();
//Nous utilisons la fonction normalize qui transforme en format JSON nos donnée qui sont
//en tableau d'objet Students
$jsonContent=$Normalizer->normalize($users,'json',['groups'=>'post:read']);



return new Response(json_encode($jsonContent));
dump($jsonContent);
die;}

 /**
     * @Route("/Usermobile/{id}", name="Usermobile/{id}")
     */
    public function Usermobileid(Request $request,$id,NormalizerInterface $Normalizer )
    {
    //Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
    $em=$this->getDoctrine()->getManager();
    $student =$em->getRepository(User::class)->find($id);
    
    //Nous utilisons la fonction normalize qui transforme en format JSON nos donnée qui sont
    //en tableau d'objet Students
    $jsonContent=$Normalizer->normalize($student,'json',['groups'=>'post:read']);
    
 
return new Response(json_encode($jsonContent));

}

 

    /**
     * @Route("/list", name="user_listpdf", methods={"GET"})
     */
    public function listpdf(UserRepository $userRepository): Response
        {
    $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $user = $userRepository->findAll();
           
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('user/listpdf.html.twig', [
            'users' => $user,
        ]);
        
        // Load HTML to Dompdf
        $dompdf->loadHtml($html);
        
        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);
   
    }

    /**
     * @Route("/new", name="user_new", methods={"GET", "POST"})
     */
    public function new(Request $request): Response
    {
        $user = new User();
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            //$file = $user->getimage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try{
                $file->move(
                    $this->getParameter('uploads_directory'),
                    $fileName
                );
            }catch(FileException $e){

            }
            $entityManager = $this->getDoctrine()->getManager();
            $user->setimage($fileName);
            $entityManager->persist($user);
            $entityManager->flush();

            return $this->redirectToRoute('user_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user/new.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }
 
/**
     * @Route("/newfront", name="user_newfront", methods={"GET", "POST"})
     */
    public function newfront(Request $request): Response
    {
        $user = new User();
        $form = $this->createForm(UserFrontType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            //$file = $user->getimage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try{
                $file->move(
                    $this->getParameter('uploads_directory'),
                    $fileName
                );
            }catch(FileException $e){

            }
            $entityManager = $this->getDoctrine()->getManager();
            $user->setimage($fileName);
            $entityManager->persist($user);
            $entityManager->flush();

            return
            $this->redirect('http://127.0.0.1:8000/user/front/' .$user->getid());
        }

        return $this->render('user/newfront.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }
 /**
     * @Route("/addUserj", name="addUserj" )
     */
    public function AddUserj(Request $request, NormalizerInterface $Normalizer )
    {
    //Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
   
    //Nous utilisons la fonction normalize qui transforme en format JSON nos donnée qui sont
    //en tableau d'objet Students
    $em=$this->getDoctrine()->getManager();
    $user=new User();
   /* $datenaissance = $request->query->get("datenaissance");
    $Nom = $request->query->get("Nom");
    $Prenom = $request->query->get("Prenom");
    $Role = $request->query->get("Role");
    $Access = $request->query->get("Access");
    $image = $request->query->get("image");
    $CIN = $request->query->get("CIN");
    $Password = $request->query->get("Password");*/
    $user->setNom($request->get('Nom'));
    $user->setPrenom($request->get('Prenom'));
   $user->setPassword($request->get("Password"));
    $user->setCIN($request->get("CIN"));
    $user->setRole($request->get("Role"));
    $user->setAccess($request->get("Access"));
    $user->setimage($request->get("image"));
   $user->setDatenaissance(date_create_from_format("Y-m-d",$request->get("datenaissance")));
    $em->persist($user);
    $em->flush();
    $jsonContent=$Normalizer->normalize($user,'json',['groups'=>'post:read']);
    
    return new Response(json_encode($jsonContent));
          
    }
    /**
     * @Route("/{id}", name="user_show", methods={"GET"})
     */
    public function show(User $user): Response
    {
        return $this->render('user/show.html.twig', [
            'user' => $user,
        ]);
    }
      
     /**
     * @Route("/front/{id}", name="user_showfront", methods={"GET"})
     */
    public function showfront(User $user): Response
    {
        return $this->render('user/showfront.html.twig', [
            'user' => $user,
        ]);
    }
     /**
     * @Route("/{id}/pdf", name="user_showpdf", methods={"GET"})
     */
    public function showpdf(User $user): Response
        {
    $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        
           
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('user/utilisateurpdf.html.twig', [
            'user' => $user,
        ]);
        
        // Load HTML to Dompdf
        $dompdf->loadHtml($html);
        
        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);
   
    }

    /**
     * @Route("/{id}/edit", name="user_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            //$file = $user->getimage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try{
                $file->move(
                    $this->getParameter('uploads_directory'),
                    $fileName
                );
            }catch(FileException $e){

            }
            $user->setimage($fileName);
            $entityManager->flush();

            return $this->redirectToRoute('user_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user/edit.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }
 /**
     * @Route("/{id}/editfront", name="user_editfront", methods={"GET", "POST"})
     */
    public function editfront(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            //$file = $user->getimage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try{
                $file->move(
                    $this->getParameter('uploads_directory'),
                    $fileName
                );
            }catch(FileException $e){

            }
            $user->setimage($fileName);
            $entityManager->flush();

            return $this->redirect('http://127.0.0.1:8000/user/front/' .$user->getid());
        }

        return $this->render('user/editfront.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="user_delete", methods={"POST"})
     */
    public function delete(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$user->getId(), $request->request->get('_token'))) {
            $entityManager->remove($user);
            $entityManager->flush();
        }

        return $this->redirectToRoute('user_index', [], Response::HTTP_SEE_OTHER);
    }
      /**
     * @Route("/{id}/delfront", name="user_deletefront", methods={"POST"})
     */
    public function deletefront(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$user->getId(), $request->request->get('_token'))) {
            $entityManager->remove($user);
            $entityManager->flush();
        }

        return $this->redirectToRoute('user_newfront', [], Response::HTTP_SEE_OTHER);
    }
   
   

   
}

    
